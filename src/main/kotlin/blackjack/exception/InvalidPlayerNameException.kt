package blackjack.exception

class InvalidPlayerNameException : IllegalArgumentException(INVALID_PLAYER_NAME_MESSAGE) {
    companion object {
        private const val INVALID_PLAYER_NAME_MESSAGE = "유효하지 않은 플레이어 이름입니다. 다시 확인해주세요."
    }
}
