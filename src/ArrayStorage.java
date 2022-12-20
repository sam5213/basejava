/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private static int index = 0;

    void clear() {
        for (int i = 0; i < index; i++) {
            storage[i] = null;
        }
        index = 0;
    }

    void save(Resume r) {
        if (index == 10000) {
            System.out.println("Storage filled");
            return;
        }
        if (index > 1) {
            for (int i = 0; i < index; i++) {
                if (storage[i].uuid == r.uuid) {
                    System.out.println("This uuid is already in the storage");
                    return;
                }
            }
        }
        storage[index++] = r;
     }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
            }
        }
        if (resume == null) {
            System.out.println("Resume not found ");
        }
        return resume;
    }

    void delete(String uuid) {
        int deleteIndex = 0;
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                deleteIndex = i;
                break;
            }
        }
        for (int i = deleteIndex; i < index + 1; i++) {
            storage[i] = storage[i + 1];
        }
        index--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] partResume = new Resume[index];
        int j = 0;
        for (int i = 0; i < index; i++) {
            if (storage[i] != null) {
                partResume[j++] = storage[i];
            }
        }
        return partResume;
    }

    int size() {
        return index;
    }
}
