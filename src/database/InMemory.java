package database;

import repositories.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InMemory<T> implements Repository<T, Long> {

    private final Map<Long, T> store =  new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);
    private final File file;
    private final Class<T> clazz;

    public InMemory(Class<T> clazz) {
        this.clazz = clazz;
        this.file = null;
    }

    public InMemory(File file, Class<T> clazz) {
        this.file = file;
        this.clazz = clazz;
        toLoad();
    }


    @Override
    public T save(T entity) {

        try {
            var idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            Long id = (Long) idField.get(entity);
            if (id == null) {
                id = seq.getAndIncrement();
                idField.set(entity, id);
            }
            store.put(id, entity);
            persist();
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar entidade", e);
        }
    }

    @Override
    public Optional<T> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @SuppressWarnings("unchecked")
    public void toLoad(){

        if (file == null || !file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<Long, T> data = (Map<Long, T>) ois.readObject();
            store.putAll(data);
            long maxId = store.keySet().stream().mapToLong(Long::longValue).max().orElse(0L);
            seq.set(maxId + 1);
        } catch (Exception e) {
            System.err.println("[WARN] Falha ao carregar dados: " + e.getMessage());
        }
    }

    private void persist(){

        if (file == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(store);
        } catch (Exception e) {
            System.err.println("[WARN] Falha ao persistir dados: " + e.getMessage());
        }
    }
    }


