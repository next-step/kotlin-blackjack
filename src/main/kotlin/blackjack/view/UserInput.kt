package blackjack.view

sealed interface UserInput {
    data class Success(val data: String) : UserInput
    data class Failure(val errorMessage: String) : UserInput
}
