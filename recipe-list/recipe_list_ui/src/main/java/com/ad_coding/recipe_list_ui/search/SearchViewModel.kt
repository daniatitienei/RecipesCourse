package com.ad_coding.recipe_list_ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad_coding.recipe_list_domain.use_case.RecipeListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: RecipeListUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.Search -> {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    val response = useCases.searchRecipe.execute(state.value.query)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recipeList = response
                        )
                    }
                }
            }

            is SearchEvent.QueryValueChange ->
                _state.update {
                    it.copy(
                        query = event.newValue
                    )
                }

            SearchEvent.ClearQuery ->
                _state.update {
                    it.copy(
                        query = ""
                    )
                }

            else -> Unit
        }
    }
}