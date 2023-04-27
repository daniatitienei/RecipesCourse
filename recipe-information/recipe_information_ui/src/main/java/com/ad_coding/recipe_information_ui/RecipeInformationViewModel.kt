package com.ad_coding.recipe_information_ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad_coding.recipe_information_domain.use_case.GetRecipeInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInformationViewModel @Inject constructor(
    private val getRecipeInformation: GetRecipeInformation,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeInformationState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isLoading = false,
                        recipe = getRecipeInformation.execute(id = id)
                    )
                }
            }
        }
    }

    fun onEvent(event: RecipeInformationEvent) {
        when (event) {
            RecipeInformationEvent.ToggleIngredients ->
                _state.update {
                    it.copy(
                        areIngredientsVisible = !it.areIngredientsVisible
                    )
                }

            else -> Unit
        }
    }
}