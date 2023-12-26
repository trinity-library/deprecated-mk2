package sisterhood.application

import sisterhood.hentai.business.HitomiService
import sisterhood.hentai.presentation.ConcreteHentaiUnitOfWork
import sisterhood.presentation.HentaiUnitOfWork

object Dependency {
    lateinit var configuration: Configuration
    lateinit var preparation: Preparation

    fun provideHentaiUnitOfWork(): HentaiUnitOfWork = ConcreteHentaiUnitOfWork(
        hitomiService = HitomiService(
            client = preparation.clientFactory.create(),
            cache = preparation.cacheFactory.create(),
            repository = preparation.repositoryFactory.create()
        )
    )

    fun provideSettingsStore() = preparation.storeFactory.create()

    fun inject(action: Scope.() -> Unit) = Scope().action()

    class Scope {
        fun configure(action: Configuration.Scope.() -> Unit) = with(Configuration.Scope()) {
            action()
            configuration = build()
        }

        fun prepare(action: Preparation.Scope.() -> Unit) = if (::configuration.isInitialized) {
            Preparation.Scope(configuration)
        } else {
            Preparation.Scope()
        }.apply {
            action()
            preparation = build()
        }
    }
}
