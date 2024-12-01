package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackPlayerTest {
    @Test
    fun `플레이어는 카드를 두장 지급 받는다`() {
        val blackJackPlayer =
            BlackJackPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        assertThat(blackJackPlayer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.JACK,
            ),
        )
    }

    @Test
    fun `2장이 아니면 에러 출력`() {
        val blackJackCards =
            BlackJackCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                ),
            )
        assertThatThrownBy { BlackJackPlayer("사람", BlackJackPlayerCards(MutableList(3) { blackJackCards.draw() })) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어는 처음에 2장만 가지고 시작해야해요")
    }

    @Test
    fun `플레이어는 가진 카드의 합이 21이 넘지 않으면 추가 카드를 뽑을 수 있다`() {
        val blackJackPlayer =
            BlackJackPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        blackJackPlayer.drawCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.TWO))
        assertThat(blackJackPlayer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.JACK,
            ),
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.TWO,
            ),
        )
    }

    @Test
    fun `플레이어는 가진 카드의 합이 21이 넘으면 추가 카드를 뽑을 수 없다`() {
        val blackJackPlayer =
            BlackJackPlayer(
                "사람",
                BlackJackPlayerCards(
                    mutableListOf(
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                        BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        blackJackPlayer.drawCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.TWO))
        assertThatThrownBy { blackJackPlayer.drawCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.THREE)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("21을 넘으면 카드를 못뽑아요")
    }
}
