package blackjack.domain

import blackjack.model.Card

class Dealer(
    private val _deck: CardDeck = CardDeckImpl(),
    val cards: Cards = Cards(),
) : GamePlay {
    val deck: CardDeck
        get() = _deck

    fun shuffle() = _deck.shuffle()

    fun deliverCard(): Card = _deck.takeOutFirstCard()
    override fun readyToPlay(initialCards: List<Card>) {
        require(initialCards.size == Game.INITIAL_CARDS_COUNT) { "잘못된 초기 카드 개수 입니다. 최초 2장만 카드를 받을 수 있습니다." }
        initialCards.forEach(cards::add)
    }

    fun stay() = cards.sum() >= STAY_CARDS_SUM

    override fun hit(card: Card) = cards.add(card)

    override fun sumCards(): Int = cards.sum()

    override fun burst(): Boolean = cards.sum() > BLACKJACK_SCORE

    override fun blackjack(): Boolean = cards.sum() == BLACKJACK_SCORE

    companion object {
        const val NAME = "딜러"
        const val MIN_CARDS_SUM = 16
        private const val STAY_CARDS_SUM = 17
    }
}
