import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countResume = 0;

    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    void save(Resume r) {
        if (countResume == storage.length) {
            System.out.println("Storage filled");
            return;
        }
        if (countResume > 0) {
            for (int i = 0; i < countResume; i++) {
                if (storage[i].uuid == r.uuid) {
                    System.out.println("This uuid is already in the storage");
                    return;
                }
            }
        }
        storage[countResume++] = r;
     }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume not found ");
        return resume;
    }

    void delete(String uuid) {
        int deleteIndex = 0;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                deleteIndex = i;
                countResume--;
                for (int j = deleteIndex; j < countResume + 1; j++) {
                    storage[j] = storage[j + 1];
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    int size() {
        return countResume;
    }
}
