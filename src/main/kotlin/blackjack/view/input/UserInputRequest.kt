package blackjack.view.input

import blackjack.view.input.converter.InputConverter

data class UserInputRequest<T>(
    val message: String,
    val inputConverter: InputConverter<T>
)
