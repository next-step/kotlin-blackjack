package blackjack.ui.printer

import blackjack.entity.ParticipantCards

@JvmInline
value class ParticipantCardsPrinter(private val participantCards: ParticipantCards) {
    fun print(): String {
        return participantCards.joinToString { card -> CardPrinter(card).print() }
    }
}
