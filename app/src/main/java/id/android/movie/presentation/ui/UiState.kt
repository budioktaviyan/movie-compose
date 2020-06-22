package id.android.movie.presentation.ui

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onActive
import androidx.compose.setValue
import androidx.compose.state
import id.android.movie.data.Result
import id.android.movie.presentation.ui.UiState.Error
import id.android.movie.presentation.ui.UiState.Loading
import id.android.movie.presentation.ui.UiState.Success

typealias RepositoryCall<T> = ((Result<T>) -> Unit) -> Unit

sealed class UiState<out T> {

  object Loading : UiState<Nothing>()

  data class Success<out T>(val data: T) : UiState<T>()
  data class Error(val exception: Exception) : UiState<Nothing>()
}

/**
 * UiState factory that updates its internal state with the [com.example.jetnews.data.Result]
 * of a repository called as a parameter.
 *
 * To load asynchronous data, effects are better pattern than using @Model classes since
 * effects are Compose lifecycle aware.
 */
@Composable
fun <T> uiStateFrom(
    repositoryCall: RepositoryCall<T>
): UiState<T> {
  var state: UiState<T> by state<UiState<T>> { Loading }

  onActive {
    repositoryCall { result ->
      state = when (result) {
        is Result.Success -> Success(result.data)
        is Result.Error -> Error(result.exception)
      }
    }
  }

  return state
}

/**
 * Helper function that loads data from a repository call. Only use in Previews!
 */
@Composable
fun <T> previewDataFrom(
    repositoryCall: RepositoryCall<T>
): T {
  var state: T? = null
  repositoryCall { result ->
    state = (result as Result.Success).data
  }

  return state!!
}