package blackjack.domain

import blackjack.model.Card

class GameDealer(
    private val _deck: CardDeck = CardDeckImpl(),
    override val cards: Cards = Cards(),
    override val name: String = "딜러",
) : Dealer {
    override val deck: CardDeck
        get() = _deck

    override fun shuffle() = _deck.shuffle()

    override fun deliverCard(): Card = _deck.takeOutFirstCard()
    override fun readyToPlay(initialCards: List<Card>) {
        require(initialCards.size == Game.INITIAL_CARDS_COUNT) { "잘못된 초기 카드 개수 입니다. 최초 2장만 카드를 받을 수 있습니다." }
        initialCards.forEach(cards::add)
    }

    override fun stay() = cards.sum() >= STAY_CARDS_SUM

    override fun hit(card: Card) = cards.add(card)

    override fun sumCards(): Int = cards.sum()

    override fun bust(): Boolean = cards.sum() > BLACKJACK_SCORE

    override fun blackjack(): Boolean = cards.sum() == BLACKJACK_SCORE

    companion object {
        private const val STAY_CARDS_SUM = 17
    }
}
