package blackjack.domain.view.model

open class ParticipantView(val name: String, val cards: List<CardView>)

class ParticipantViewResult(
    name: String,
    cards: List<CardView>,
    val score: Int,
): ParticipantView(name = name, cards = cards)
