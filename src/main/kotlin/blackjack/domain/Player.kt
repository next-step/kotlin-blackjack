package blackjack.domain

class Player(val name: Nickname, private val dealer: Dealer) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> = _cards

    fun receiveCard() {
        val card = dealer.dealCard()
        _cards.add(card)

        if (calculateScore() > BLACKJACK) {
            throw IllegalStateException("${name.value} 플레이어의 카드 합이 21을 초과했습니다.")
        }
    }

    private fun calculateScore(): Int {
        var score = cards.sumOf { it.rank.score }
        var countOfAces = cards.count { it.rank == Rank.ACE }

        while (score > BLACKJACK && countOfAces > 0) {
            score -= 10
            countOfAces -= 1
        }

        return score
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
