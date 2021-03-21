package blackjack.view.converter

import blackjack.domain.Card

object CardResultStringConverter : ResultStringConverter<Card> {
    override fun convert(t: Card): String {
        val cardSpellResultString = CardSpellResultStringConverter.convert(t.spell)
        val cardSuitResultString = CardSuitResultStringConverter.convert(t.suit)
        return "$cardSpellResultString$cardSuitResultString"
    }
}
