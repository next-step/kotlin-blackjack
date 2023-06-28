package blackjack.scorerule

import blackjack.common.service.DeckManager
import blackjack.scorerule.domain.ScoreDealer
import blackjack.scorerule.domain.ScorePlayer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackScoreKoTest : StringSpec({
    "참여자들에게 카드를 두 장 나누어 진다" {
        val players = arrayOf(ScorePlayer("test1"), ScorePlayer("test2"))
        val dealer = ScoreDealer()
        val deckManager = DeckManager()
        dealer.beginRound(deckManager, players)
        players.forEach { it.hands().size shouldBe 2 }
    }
})
