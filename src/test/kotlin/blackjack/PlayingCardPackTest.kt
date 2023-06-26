package blackjack

import blackjack.domain.CardType
import blackjack.domain.CardValue
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCardPack
import blackjack.domain.PlayingCardPackFactory
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotContainInOrder
import io.kotest.matchers.shouldBe

class PlayingCardPackTest : FreeSpec({

    "새 카드 팩은 서로 다른 카드 52장으로 이루어진다." {
        val pack = PlayingCardPackFactory.createPack()
        pack.remainCount() shouldBe 52

        val playingCardsInPack = mutableListOf<PlayingCard>()

        while (pack.remainCount() > 0) {
            playingCardsInPack.add(pack.pick())
        }

        playingCardsInPack shouldContainAll allPlayingCards()
    }

    "카드 팩은 섞을 수 있다." {
        val allPlayingCards = allPlayingCards()
        val pack = PlayingCardPack(allPlayingCards)
        pack.shuffle()
        val pickAllCards = pack.pickAllCards()

        pickAllCards shouldNotContainInOrder allPlayingCards
    }
}) {
    companion object {

        private fun allPlayingCards(): List<PlayingCard> {
            return CardType.values().flatMap { type -> CardValue.values().map { value -> PlayingCard(type, value) } }
        }

        private fun PlayingCardPack.pickAllCards(): List<PlayingCard> {
            val playingCardsInPack = mutableListOf<PlayingCard>()

            while (this.remainCount() > 0) {
                playingCardsInPack.add(this.pick())
            }

            return playingCardsInPack
        }
    }
}
