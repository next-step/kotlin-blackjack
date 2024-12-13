package blackjack.service

import blackjack.dto.BlackJackResult
import blackjack.domain.Deal
import blackjack.domain.GameResultCalculator
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player
import blackjack.repository.GameRepository

class BlackJackService(private val gameRepository: GameRepository) {
    fun getGameInfo(): Game {
        return gameRepository.findAll()
    }

    fun initPlayers(dealerName: String, playersList: List<String>) {
        val dealer = Dealer(dealerName)
        val players = playersList.map { Player(it) }.toSet()
        gameRepository.saveGame(Game(dealer, players))
    }

    fun startGame(): Game {
        val game = gameRepository.findAll()
        initDealer(game.dealer)
        initPlayers(game.players)
        return game
    }

    private fun initDealer(dealer: Dealer) {
        dealer.getDealerBlackJack().addCardCount(Deal.giveCards(DEFAULT_FACE_UP, false))
        dealer.getDealerBlackJack().addCardCount(Deal.giveCards(DEFAULT_FACE_UP, true))
    }

    private fun initPlayers(players: Set<Player>) {
        players.forEach { player ->
            player.getPlayerBlackJack().addCardCount(Deal.giveCards(INIT_FACE_UP))
        }
    }

    fun gameContinue(playerName: String): Player {
        val player = gameRepository.findPlayerByName(playerName)
        val playerBlackJack = player.getPlayerBlackJack()
        playerBlackJack.addCardCount(Deal.giveCards(DEFAULT_FACE_UP))
        return player
    }


    fun gameContinueDealer(): Dealer {
        val dealer = gameRepository.findDealer()
        val dealerBlackJack = dealer.getDealerBlackJack()
        dealerBlackJack.addCardCount(Deal.giveCards(DEFAULT_FACE_UP))
        return dealer
    }

    fun getGameResult(): BlackJackResult {
        val game = gameRepository.findAll()
        return GameResultCalculator.calculateGameResult(game)
    }

    companion object {
        private const val INIT_FACE_UP = 2
        private const val DEFAULT_FACE_UP = 1
    }
}
