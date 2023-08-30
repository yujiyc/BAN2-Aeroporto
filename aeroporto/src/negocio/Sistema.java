package negocio;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.MongoException;

import persistencia.*;
import dados.*;
import excecoes.*;

public class Sistema {
    
    private static Sistema instance = null;
    private static AplicaTesteDAO aplicaTesteDAO = null;
    private static AviaoDAO aviaoDAO = null;
    private static ModeloDAO modeloDAO = null;
    private static ControladorAereoDAO controladorAereoDAO = null;
    private static EmpregadoDAO empregadoDAO = null;
    private static SindicatoDAO sindicatoDAO = null;
    private static TecnicoDAO tecnicoDAO = null;
    private static TestagemDAO testagemDAO = null;
    private static TesteDAO testeDAO = null;

    public static Sistema getSistema() throws MongoException {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    private Sistema() throws MongoException {
        aplicaTesteDAO = AplicaTesteDAO.getInstance();
        aviaoDAO = AviaoDAO.getInstance();
        modeloDAO = ModeloDAO.getInstance();
        controladorAereoDAO = ControladorAereoDAO.getInstance();
        empregadoDAO = EmpregadoDAO.getInstance();
        sindicatoDAO = SindicatoDAO.getInstance();
        tecnicoDAO = TecnicoDAO.getInstance();
        testagemDAO = TestagemDAO.getInstance();
        testeDAO = TesteDAO.getInstance();
    }

    //-------------------------------------------------------SINDICATO------------------------------------------------------------------//

    public void insereSindicato(Sindicato sindicato) throws SelectException, InsertException{
        sindicatoDAO.insert(sindicato);
    }

    public void deletaSindicato(Sindicato sindicato) throws DeleteException{
        sindicatoDAO.delete(sindicato);
    }

    public Sindicato buscaSindicato(ObjectId nroMembro) throws SelectException{
        return sindicatoDAO.select(nroMembro);
    }

    public List<Sindicato> buscaSindicatos() throws SelectException{
        return sindicatoDAO.selectAll();
    }

    //-------------------------------------------------------EMPREGADO------------------------------------------------------------------//

    public void insereEmpregado(Empregado empregado) throws InsertException, SelectException {

        if( empregado instanceof Tecnico ){
            Tecnico tecnico = (Tecnico) empregado;
            tecnicoDAO.insert(tecnico);
        }else if (empregado instanceof ControladorAereo ){
            ControladorAereo controladorAereo = (ControladorAereo) empregado;
            controladorAereoDAO.insert(controladorAereo);
        }else{
            empregadoDAO.insert(empregado);
        }
    }

    public void deletaEmpregado(Empregado empregado) throws DeleteException {

        if( empregado instanceof Tecnico ){
            Tecnico tecnico = (Tecnico) empregado;
            tecnicoDAO.delete(tecnico);
        }else if (empregado instanceof ControladorAereo ){
            ControladorAereo controladorAereo = (ControladorAereo) empregado;
            controladorAereoDAO.delete(controladorAereo);
        }

        empregadoDAO.delete(empregado);
    }

    public Empregado buscaEmpregado(ObjectId matricula) throws SelectException {
        return empregadoDAO.select(matricula);
    }

    public Tecnico buscaTecnico(ObjectId matricula) throws SelectException {
        return tecnicoDAO.select(matricula);
    }

    public ControladorAereo buscaControladorAereo(ObjectId matricula) throws SelectException {
        return controladorAereoDAO.select(matricula);
    }

    public List<Empregado> buscaEmpregados() throws SelectException {
        return empregadoDAO.selectAll();
    }

    public List<Tecnico> buscaTecnicos() throws SelectException {
        return tecnicoDAO.selectAll();
    }

    public List<ControladorAereo> buscaControladoresAereos() throws SelectException {
        return controladorAereoDAO.selectAll();
    }
    
    public void insereModeloTecnico(Tecnico tecnico, Modelo modelo) throws SelectException, InsertException {
        tecnicoDAO.insereModelo(tecnico, modelo);
    }

    public void deleteModeloTecnico(Tecnico tecnico, Modelo modelo) throws DeleteException {
        tecnicoDAO.deleteModelo(tecnico, modelo);
    }

    //-------------------------------------------------------MODELO------------------------------------------------------------------//

    public void insereModelo(Modelo modelo) throws  SelectException, InsertException {
        modeloDAO.insert(modelo);
    }

    public void deletaModelo(Modelo modelo) throws  DeleteException {
        modeloDAO.delete(modelo);
    }

    public Modelo buscaModelo(ObjectId codigo) throws SelectException {
        return modeloDAO.select(codigo);
    }

    public List<Modelo> buscaModelos() throws SelectException {
        return modeloDAO.selectAll();
    }

    //-------------------------------------------------------AVIAO------------------------------------------------------------------//

    public void insereAviao(Aviao aviao) throws InsertException, SelectException {
        aviaoDAO.insert(aviao);
    }

    public void deletaAviao(Aviao aviao) throws DeleteException {
        aviaoDAO.delete(aviao);
    }

    public void atualizaAviao(Aviao aviao) throws UpdateException {
        aviaoDAO.update(aviao);
    }

    public Aviao buscaAviao(ObjectId nroRegistro) throws SelectException {
        return aviaoDAO.select(nroRegistro);
    }

    public List<Aviao> buscaAvioes() throws SelectException {
        return aviaoDAO.selectAll();
    }

    //-------------------------------------------------------TESTE------------------------------------------------------------------//

    public void insereTeste(Teste teste) throws SelectException, InsertException {
        testeDAO.insert(teste);
    }

    public void deletaTeste(Teste teste) throws DeleteException {
        testeDAO.delete(teste);
    }

    public Teste buscaTeste(ObjectId idAnac) throws SelectException {
        return testeDAO.select(idAnac);
    }

    public List<Teste> buscaTestes() throws SelectException {
        return testeDAO.selectAll();
    }

    //-------------------------------------------------------APLICA TESTE------------------------------------------------------------------//

    public void insereAplicaTeste(AplicaTeste aplicaTeste) throws InsertException, SelectException {
        aplicaTesteDAO.insert(aplicaTeste);
    }

    public void deletaAplicaTeste(AplicaTeste aplicaTeste) throws DeleteException {
        aplicaTesteDAO.delete(aplicaTeste);
    }

    //TALVEZ SEJA NECESSÁRIO MODIFICAR A LOGICA DE BUSCA DESTE MÉTODO
    public AplicaTeste buscaAplicaTeste(ObjectId id) throws SelectException {
        return aplicaTesteDAO.select(id);
    }

    public List<AplicaTeste> buscaAplicaTestes() throws SelectException {
        return aplicaTesteDAO.selectAll();
    }

    //-------------------------------------------------------TESTAGEM------------------------------------------------------------------//

    public void insereTestagem(Testagem testagem) throws SelectException, InsertException {
        testagemDAO.insert(testagem);
    }

    public void deletaTestagem(Testagem testagem) throws DeleteException {
        testagemDAO.delete(testagem);
    }

    //TALVEZ SEJA NECESSÁRIO MODIFICAR A LOGICA DE BUSCA DESTE MÉTODO
    public Testagem buscaTestagem(ObjectId id) throws SelectException {
        return testagemDAO.select(id);
    }

    public List<Testagem> buscaTestagens() throws SelectException {
        return testagemDAO.selectAll();
    }

}
