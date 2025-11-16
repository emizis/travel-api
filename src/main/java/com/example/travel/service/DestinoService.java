package com.example.travel.service;
import com.example.travel.model.Destino;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*; import java.util.concurrent.atomic.AtomicLong; import java.util.stream.Collectors;
@Service
public class DestinoService {
    private final Map<Long, Destino> repo = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);
    public Destino criarDestino(String n,String l,String dsc,Double p){
        Long id=idGen.getAndIncrement();
        Destino d=new Destino(id,n,l,dsc,p); repo.put(id,d); return d;}
    public List<Destino> listarTodos(){return new ArrayList<>(repo.values());}
    public Destino buscarPorId(Long id){
        Destino d = repo.get(id);
        if(d==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return d;}
    public List<Destino> pesquisar(String nome,String loc){
        return repo.values().stream().filter(d->{
            boolean ok=true;
            if(nome!=null&&!nome.isBlank()) ok&=d.getNome().toLowerCase().contains(nome.toLowerCase());
            if(loc!=null&&!loc.isBlank()) ok&=d.getLocalizacao().toLowerCase().contains(loc.toLowerCase());
            return ok;}).collect(Collectors.toList());}
    public Destino atualizar(Long id,String n,String l,String ds,Double p){
        Destino d=buscarPorId(id);
        d.setNome(n); d.setLocalizacao(l); d.setDescricao(ds); d.setPreco(p); return d;}
    public void deletar(Long id){ if(repo.remove(id)==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
    public Destino avaliar(Long id,int nota){
        if(nota<1||nota>10) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Destino d=buscarPorId(id);
        double tot=d.getNotaMedia()*d.getQuantidadeAvaliacoes();
        int nv=d.getQuantidadeAvaliacoes()+1;
        double media=(tot+nota)/nv;
        d.setQuantidadeAvaliacoes(nv);
        d.setNotaMedia(Math.round(media*100.0)/100.0);
        return d;}
}