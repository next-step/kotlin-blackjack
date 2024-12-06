package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackNormalPlayerTest {
    @Test
    fun `플레이어는 카드를 두장 지급 받는다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        assertThat(blackJackNormalPlayer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.JACK,
            ),
        )
    }

    @Test
    fun `2장이 아니면 에러 출력`() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                ),
            )
        assertThatThrownBy { BlackJackNormalPlayer("사람", BlackJackPlayerCards(MutableList(3) { blackJackDeck.draw() })) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어는 처음에 2장만 가지고 시작해야해요")
    }

    @Test
    fun `플레이어는 가진 카드의 합이 21이 넘지 않으면 추가 카드를 뽑을 수 있다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        val blackJackDeck = BlackJackDeck(listOf(BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.TWO)))
        blackJackNormalPlayer.drawCard(blackJackDeck)
        assertThat(blackJackNormalPlayer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.JACK,
            ),
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.TWO,
            ),
        )
    }

    @Test
    fun `플레이어는 가진 카드의 합이 21이 넘으면 추가 카드를 뽑을 수 없다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        val blackJackDeck =
            BlackJackDeck(
                listOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.TWO),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.THREE),
                ),
            )
        blackJackNormalPlayer.drawCard(blackJackDeck)
        assertThat(blackJackNormalPlayer.blackJackPlayerCards.cards.size).isEqualTo(3)
        assertThat(blackJackNormalPlayer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
            BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
            BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.THREE),
        )
    }

    @Test
    fun `플레이어는 자신이 가진 카드의 최적의 합을 구할수 있다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    ),
                ),
            )

        assertThat(blackJackNormalPlayer.getBestSum()).isEqualTo(21)
    }

    @Test
    fun `플레이어는 이기면 배팅 금액 만큼 이익을 얻는다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    ),
                ),
                bet = 10000,
            )
        blackJackNormalPlayer.win()
        assertThat(blackJackNormalPlayer.profit).isEqualTo(10000)
    }

    @Test
    fun `플레이어는 지면 배팅금액 만큼 손해를 본다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    ),
                ),
                bet = 10000,
            )
        blackJackNormalPlayer.lose()
        assertThat(blackJackNormalPlayer.profit).isEqualTo(-10000)
    }

    @Test
    fun `플레이어는 비기면 이익의 변화가 없다`() {
        val blackJackNormalPlayer =
            BlackJackNormalPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
                        BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    ),
                ),
                bet = 10000,
            )
        blackJackNormalPlayer.draw()
        assertThat(blackJackNormalPlayer.profit).isEqualTo(0)
    }
}
