package blackjack.domain

import blackjack.model.Card
import java.util.UUID

class FakePlayer(
    val name: String = UUID.randomUUID().toString(),
    private val hit: Boolean = false,
    private val sumCards: Int = 0,
    private val burst: Boolean = false,
    private val blackjack: Boolean = false,
) : Player {

    override fun readyToPlay(initialCards: List<Card>) {
        // do nothing
    }

    override fun hit(card: Card): Boolean = hit

    override fun sumCards(): Int = sumCards

    override fun burst(): Boolean = burst

    override fun blackjack(): Boolean = blackjack
}

class FakeDealer(
    private val hit: Boolean = false,
    private val sumCards: Int = 0,
    private val burst: Boolean = false,
    private val blackjack: Boolean = false,
) : Player {

    override fun readyToPlay(initialCards: List<Card>) {
        // do nothing
    }

    override fun hit(card: Card): Boolean = hit

    override fun sumCards(): Int = sumCards

    override fun burst(): Boolean = burst

    override fun blackjack(): Boolean = blackjack
}
