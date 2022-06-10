package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.Name

private const val MIN_PLAYER_SIZE = 2

class Players(
    val players: List<Player>
) {
    val names: List<Name>
        get() = players.map { it.name }

    val playable: List<Player>
        get() = players.filter { it.participantInformation.isPlay() }

    val notBustCount: Int
        get() = players.count { it.participantInformation.status != Status.BUST }

    init {
        require(players.size >= MIN_PLAYER_SIZE) { "플레이어는 최소 $MIN_PLAYER_SIZE 명 이상이어야 합니다." }
    }

    fun ready(cardDeck: CardDeck): Unit = players.forEach { it.ready(cardDeck) }

    fun hit(hitAction: (Player) -> Unit) {
        playable.forEach { hitAction(it) }
    }

    fun winToDealerBust() {
        players.forEach { it.winToDealerBust() }
    }

    fun score(dealer: Dealer) {
        val participants = buildList {
            addAll(players)
            add(dealer)
        }
        players.forEach { it.score(participants) }
    }
}
