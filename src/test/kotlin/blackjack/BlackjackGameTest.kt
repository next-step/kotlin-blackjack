package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.CardSuit
import blackjack.domain.Dealer
import blackjack.domain.Player
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackjackGameTest {
    private lateinit var dealer: Dealer
    private lateinit var players: List<Player>
    private lateinit var blackjackGame: BlackjackGame

    @BeforeEach
    fun beforeEach(){
        dealer = Dealer()
        players = listOf(Player(), Player())
        blackjackGame = BlackjackGame(dealer, players)

    }

    @DisplayName("플레이어는 게임 시작 시 카드 두 장을 지급받는다.")
    @Test
    fun initPlayerGetCards() {
        val dealer = Dealer()
        val players = listOf(Player(), Player())
        players.forAll { player ->
            player.cards.isEmpty() shouldBe true
        }

        val blackjackGame = BlackjackGame(dealer, players)
        blackjackGame.players.forAll { player ->
            player.cards.size shouldBe 2
        }
    }

    @DisplayName("플레이어가 한명이라도 카드를 뽑을 수 있는 상태라면 isNotFinish 메서드는 true를 반환한다.")
    @Test
    fun isNotFinishReturnTrue() {
        val dealer = Dealer()
        val players = listOf(Player(), Player())

        val blackjackGame = BlackjackGame(dealer, players)
        val actual = blackjackGame.isNotFinished()
        val expect = true

        actual shouldBe expect
    }

    @DisplayName("플레이어가 모두 카드를 뽑을 수 없는 상태라면 isNotFinish 메서드는 false를 반환한다.")
    @Test
    fun isNotFinishReturnFalse() {
        val dealer = Dealer()
        val players = listOf(Player(), Player())
        val card = Card.of(CardSuit.DIAMOND, CardRank.KING)
        players.forEach { it.addCards(listOf(card, card, card)) }

        val blackjackGame = BlackjackGame(dealer, players)
        val actual = blackjackGame.isNotFinished()
        val expect = false

        actual shouldBe expect
    }
}
