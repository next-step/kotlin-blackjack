package blackjack.domain.player

import blackjack.domain.Cards

class FakeGamer(name: String, cards: Cards) : Gamer(name, cards) {

    override fun isNotTakeable(): Boolean {
        throw UnsupportedOperationException("구현되지 않은 메서드")
    }
}
