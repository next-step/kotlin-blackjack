package blackjack.domain.view.model

class PlayerNamesInput(text: String) {

    val playerNames: List<String> = text.split(DELIMITER)
        .map { it.trim() }

    companion object {
        private const val DELIMITER: Char = ','
    }
}
