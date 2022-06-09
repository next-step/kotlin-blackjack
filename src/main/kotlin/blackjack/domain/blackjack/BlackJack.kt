package blackjack.domain.blackjack

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJack(
    private val dealer: Dealer = Dealer(),
    private val players: Players
) {
    val hittablePlayers get() = players.hittablePlayers()
    val isEnd: Boolean get() = players.isEnd() && dealer.isEnd

    init {
        players.addTwoCard(dealer)
        repeat(2) { dealer.addCard() }
    }

    fun giveCard(player: Player) {
        require(players.contains(player)) { "존재하지 않는 참가자입니다" }

        dealer.giveCard(player)
    }

    fun playDealer() {
        while (!dealer.isEnd) {
            dealer.addCard()
        }
    }

    fun result(): BlackJackResult {
        check(isEnd) { "게임이 종료되어야 결과를 확인할 수 있습니다" }

        return BlackJackResult.of(players.players, dealer)
    }
}
