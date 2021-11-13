package blackJack.domain

// 카드를 받으면, 바로 상태와 점수 그리고 카드가 갱신되어야함.
data class Player(val playerName: String, val status: PlayerStatus) {

    fun receiveCard(card: Card): Player {
        return this.copy(playerName = playerName, status = status.update(card))
    }

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName, PlayerStatus.of())
        }
    }
}
