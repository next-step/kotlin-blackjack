package blackjack.view.converter

import blackjack.domain.CardSpell

object CardSpellResultStringConverter : ResultStringConverter<CardSpell> {
    override fun convert(t: CardSpell): String {
        return when (t) {
            CardSpell.ACE -> "A"
            CardSpell.TWO -> "2"
            CardSpell.THREE -> "3"
            CardSpell.FOUR -> "4"
            CardSpell.FIVE -> "5"
            CardSpell.SIX -> "6"
            CardSpell.SEVEN -> "7"
            CardSpell.EIGHT -> "8"
            CardSpell.NINE -> "9"
            CardSpell.TEN -> "10"
            CardSpell.JACK -> "J"
            CardSpell.QUEEN -> "Q"
            CardSpell.KING -> "K"
        }
    }
}
