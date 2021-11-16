package blackjack.error

import blackjack.domain.playingcard.PlayingCard

class DuplicatePlayingCardException(private val playingCard: List<PlayingCard>) : RuntimeException() {
    override val message = MESSAGE

    companion object {
        private const val MESSAGE = "이미 덱에 존재하는 카드입니다."
    }
}
