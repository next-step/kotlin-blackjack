package blackjack.domain

class Dealer(
    playerName: PlayerName,
    cards: List<Card> = listOf(),
): Participant(playerName, cards) {

    override fun addCard(card: Card): DrawCard {
        check(canDraw()) { "딜러의 카드가 17점 이상이라 카드를 더이상 뽑을 수 없습니다." }
        cards.add(card)
        return card.toDrawCard()
    }

    override fun canDraw(): Boolean = cards.calculateTotalScore() <= 16

    override fun stopDraw() {
        throw IllegalStateException("딜러는 수동으로 카드 뽑기 여부를 결정할 수 없습니다.")
    }
}
