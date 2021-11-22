package blackjack.exception

class NotExistDealerException : RuntimeException(MESSAGE_NOT_EXIST_DEALER) {

    companion object {
        private const val MESSAGE_NOT_EXIST_DEALER = "딜러가 존재하지 않습니다. 다시 확인해주세요."
    }
}
