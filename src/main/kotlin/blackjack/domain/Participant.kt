package blackjack.domain

interface Participant {
    val name: Nickname
    val cards: List<Card>

    fun receiveCard(card: Card)
}

fun Participant.calculateScore(): Int {
    var score = this.cards.sumOf { it.rank.score }
    var countOfAces = this.cards.count { it.rank == Rank.ACE }

    while (score > 21 && countOfAces > 0) {
        score -= 10
        countOfAces -= 1
    }

    return score
}
