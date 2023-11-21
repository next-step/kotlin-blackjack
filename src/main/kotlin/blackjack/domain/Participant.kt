package blackjack.domain

private const val ACE_LOW_VALUE = 1
private const val ACE_HIGH_VALUE = 11

interface Participant {
    val name: Nickname
    val cards: List<Card>

    fun receiveCard(card: Card)

    fun canDraw(): Boolean
}

fun Participant.calculateScore(): Int {
    var score = this.cards.sumOf { it.rank.score }
    var countOfAces = this.cards.count { it.rank == Rank.ACE }

    while (score > BLACKJACK && countOfAces > 0) {
        score -= ACE_HIGH_VALUE - ACE_LOW_VALUE
        countOfAces -= 1
    }

    return score
}
