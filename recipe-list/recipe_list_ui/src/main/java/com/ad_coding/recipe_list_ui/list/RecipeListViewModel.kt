package com.ad_coding.recipe_list_ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad_coding.recipe_list_domain.use_case.RecipeListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val useCases: RecipeListUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeListState())
    val state = _state.asStateFlow()

    init {
        fetchRecipes()
    }

    fun onEvent(event: RecipeListEvent) {
        when (event) {
            RecipeListEvent.Refresh -> fetchRecipes()
            else -> Unit
        }
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            useCases.getRecipeList.execute()
                .collectLatest { recipeList ->
                    _state.update {
                        it.copy(
                            recipeList = recipeList,
                            isLoading = false
                        )
                    }
                }
        }
    }
}