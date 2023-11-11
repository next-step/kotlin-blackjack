package blackjack.domain

class Player(val name: Nickname, private val dealer: Dealer) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> = _cards

    fun receiveCard() {
        val card = dealer.dealCard()
        _cards.add(card)

        if (calculateScore() > MAX_CARD_SCORE) {
            throw IllegalStateException("${name.value} 플레이어의 카드 합이 ${MAX_CARD_SCORE}을 초과했습니다.")
        }
    }

    fun calculateScore(): Int {
        var score = cards.sumOf { it.rank.score }
        var countOfAces = cards.count { it.rank == Rank.ACE }

        while (score > MAX_CARD_SCORE && countOfAces > 0) {
            score -= 10
            countOfAces -= 1
        }

        return score
    }

    companion object {
        private const val MAX_CARD_SCORE = 21
    }
}
