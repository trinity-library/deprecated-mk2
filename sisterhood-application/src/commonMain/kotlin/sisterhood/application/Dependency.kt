package sisterhood.application

object Dependency {
    lateinit var configuration: Configuration
    lateinit var preparation: Preparation

    fun inject(action: Scope.() -> Unit) {

    }

    class Scope {
        fun configure(action: Configuration.Scope.() -> Unit) = with(Configuration.Scope()) {
            action()
            build()
        }

        fun prepare(action: Preparation.Scope.() -> Unit) = with(Preparation.Scope()) {
            action()
        }
    }
}
