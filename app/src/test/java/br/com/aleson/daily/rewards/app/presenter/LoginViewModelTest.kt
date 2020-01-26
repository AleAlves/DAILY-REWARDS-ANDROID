package br.com.aleson.daily.rewards.app.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import br.com.aleson.core.tools.coretools.retrofit.domain.BaseResponse
import br.com.aleson.core.tools.coretools.retrofit.domain.HTTPResponse
import br.com.aleson.daily.rewards.app.data.repository.Repository
import br.com.aleson.daily.rewards.app.data.usecase.UseCase
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseHandler
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseRequest
import br.com.aleson.daily.rewards.app.data.usecase.UseCaseResponse
import br.com.aleson.daily.rewards.app.domain.security.PublicKey
import br.com.aleson.daily.rewards.app.domain.usecases.GetPublicKeyRequest
import br.com.aleson.daily.rewards.app.domain.usecases.PublicKeyUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class LoginViewModelTest {

    lateinit var publicKeyCase: PublicKeyUseCase
    lateinit var repository: Repository
    lateinit var lyfeclircleOwner: LifecycleOwner
    lateinit var getPublicKeyRequest: GetPublicKeyRequest
    private lateinit var publicKey: PublicKey
    private lateinit var useCaseHandler: UseCaseHandler
    private lateinit var loginViewModel: LoginViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getDetails: PublicKeyUseCase

    //Request
    @Captor
    lateinit var getPublicKey: ArgumentCaptor<PublicKeyUseCase>


    //Callback
    @Captor
    lateinit var getPublicKeyCallback: ArgumentCaptor<UseCase<UseCaseRequest, UseCaseResponse>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getPublicKeyRequest = mock(GetPublicKeyRequest::class.java)
        lyfeclircleOwner = mock(LifecycleOwner::class.java)
        useCaseHandler = mock(UseCaseHandler::class.java)
        publicKeyCase = mock(PublicKeyUseCase::class.java)
        publicKey = PublicKey("123")
        loginViewModel = LoginViewModel(useCaseHandler, publicKeyCase)
    }

    @Test
    fun isValidReturnTrueTest() {

        loginViewModel.init()
        loginViewModel.loadPublicKey()
        loginViewModel.publicKey.observeForever { publicKey ->
            Assert.assertEquals("Key", publicKey)
        }
    }

    @Test
    fun link_to_medium_test() {

    }

}