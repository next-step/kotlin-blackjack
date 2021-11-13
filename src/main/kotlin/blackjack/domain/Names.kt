package blackjack.domain

import blackjack.utils.StringUtils

data class Names(val names: Set<Name>) {

    companion object {
        private const val DELIMITER = ","

        fun generateNames(text: String?): Names {
            requireNotNull(text)
            val splitText = StringUtils.splitText(text, DELIMITER)
            return Names(splitText.map { Name(it) }.toSet())
        }
    }
}
