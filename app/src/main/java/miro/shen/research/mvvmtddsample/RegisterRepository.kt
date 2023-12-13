package miro.shen.research.mvvmtddsample

class RegisterRepository : IRegisterRepository {
    override fun register(
        loginId: String,
        password: String,
        listener: IRegisterRepository.RegisterCallback
    ) {

    }

}
