package blackjack.controller

import blackjack.model.BettingMoneyProvider
import blackjack.model.BlackjackDealerMoreCardScoreLimitConsumer
import blackjack.model.BlackjackJudgeConsumer
import blackjack.model.BlackjackPlayerConsumer
import blackjack.model.BlackjackPlayersCardCountConsumer
import blackjack.model.BlackjackPlayersScoreConsumer
import blackjack.model.BlackjackRevenueJudge
import blackjack.model.CardDeck
import blackjack.model.MoreWantedCardPredicate
import blackjack.model.PlayerNamesProvider
import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackParticipants

data class BlackjackGame(
    private val moreWantedCardPredicate: MoreWantedCardPredicate,
    private val bettingMoneyProvider: BettingMoneyProvider,
    private val playerNamesProvider: PlayerNamesProvider,
    private val blackjackDealerMoreCardScoreLimitConsumer: BlackjackDealerMoreCardScoreLimitConsumer,
    private val blackjackPlayerConsumer: BlackjackPlayerConsumer,
    private val blackjackPlayersCardCountConsumer: BlackjackPlayersCardCountConsumer,
    private val blackjackPlayersScoreConsumer: BlackjackPlayersScoreConsumer,
    private val blackjackJudgeConsumer: BlackjackJudgeConsumer,
) {
    fun start() {
        val deck = CardDeck()
        val dealer = BlackjackDealer(deck, blackjackDealerMoreCardScoreLimitConsumer)
        val players = playerNamesProvider.names().registerPlayer(
            deck,
            bettingMoneyProvider,
            blackjackPlayerConsumer,
            moreWantedCardPredicate
        )

        BlackjackParticipants.withDealerAndDraw(players, dealer, blackjackPlayersCardCountConsumer)
        blackjackPlayersScoreConsumer.consumePlayers(dealer, players)
        blackjackJudgeConsumer.consume(BlackjackRevenueJudge(dealer, players))
    }
}
