package blackjack.view

fun tokenizeUserNames(input: String): List<String> {
    return input
        .split(",")
        .filterNot { it.isBlank() }
        .map { it.trim() }
}
