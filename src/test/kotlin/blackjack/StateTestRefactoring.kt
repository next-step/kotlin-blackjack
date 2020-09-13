package blackjack

/*
마지막 수업 때 메모했던 라이브 코딩 입니다. 단위 테스트와 컴파일 우선 진행 후 참고 계획
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.internal.bytebuddy.dynamic.loading.ByteArrayClassLoader
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException

const val CLUBS_ACE_RE: PlayingCard = PlayingCard(Suit.CLUB, Denomination.ACE)
const val CLUBS_KING: PlayingCard = PlayingCard(Suit.CLUB, Denomination.KING)
const val CLUBS_QEEN: PlayingCard = PlayingCard(Suit.CLUB, Denomination.QUEEN)
const val CLUBS_FIVE: PlayingCard = PlayingCard(Suit.CLUB, Denomination.FIVE)

abstract sealed class Finished() : State {
    override val isFinished: Boolean = true
    override fun addCard(card: PlayingCard) {
        throw UnsupportedOperationException()
    }
}

class Stay : Finished() {
    override val betRate = 1.0
}

class Bust : Finished() {
    override val betRate = 0
}

class BlackJack : Finished()

class StateTest {
    @Test
    fun blackjack() {
        val state = State(CLUBS_ACE, CLUBS_KINGx)   // fake class
        assertThat(state).isInstanceOf(Hit::class.java)
        assertThat(state).isFinished.isFalse()
    }

    @Test
    fun hit() {
        val state = State(CLUBS_KING, CLUBS_QUUN)
        assertThat(state.hands.addCard(CLUBS_FIVE)).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `win`() {
        val state: State = Hit(Hands(CLUBS_KING, CLUBS_QEEN))
        assertThatExceptionOfType(UnsupportedOperationException::class.java).isThrownBy { state.win(17) }
    }

    @Test
    fun `win when stay`() {
    }

    @Test
    fun `lose when burst`() {
    }

    @Test
    fun
}

class Stay(private val dealer: Int, val user: Int){

}

fun State(first: PlayingCard, second: PlayingCard) {
    val hands = Hands(first, second)
    if (hands.isBlackJack) {
        return BlackJack()
    }
    return Hit(hands)
}

class Hit : State {
    override val isFinished: Boolean = false
    override val betRate:
        override

    fun addCard(card: PlayingCard) {
        hands.add(card)
        if (hands.isBust) {
            return Bust()
        }
        if (hands.calculate() == 21) {

        }
    }
}

class win : State {

}

interface State {
    val isFinished: Boolean
    val betRate: Double

    // fun addCard(card: PlayingCard)
    fun win(score: Int): String
}

 */
