package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.enums.TipoConta;
import org.example.exceptions.UserException;
import org.example.models.Conta;
import org.example.models.ContaCorrente;
import org.example.models.Pessoa;
import org.example.models.PessoaFisica;
import org.example.services.ContaService;
import org.example.services.PessoaService;
import org.junit.Before;
import org.junit.Test;

public class PessoaServiceTest {
    private PessoaService pessoaService;
    private ContaService contaService;

    @Before
    public void init() {
        pessoaService = PessoaService.getInstance();
        contaService = ContaService.getInstance();
    }

    @Test
    public void evaluatesExpression() {
        assertTrue(pessoaService.login("User1"));
    }

    @Test
    public void testeIncrementoIDDao() throws UserException {
        Pessoa p1 = pessoaService.create(new PessoaFisica().setCpf("123").setNome("Teste"));
        assertEquals(6, p1.getId());

        Pessoa p2 = pessoaService.create(new PessoaFisica().setCpf("123").setNome("Teste"));
        assertEquals(7, p2.getId());

        Conta c1 = contaService.abrirConta(new PessoaFisica(), new ContaCorrente(), TipoConta.CORRENTE);
        assertEquals(8, c1.getId());

        Conta c2 = contaService.abrirConta(new PessoaFisica(), new ContaCorrente(), TipoConta.CORRENTE);
        assertEquals(9, c2.getId());
    }
}