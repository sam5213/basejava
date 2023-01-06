package com.urise.webapp.storage;
import java.util.Arrays;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private int countResume;
    Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
    }

    public void save(Resume r) {
        int index = getIndex(r.uuid);
        if (countResume == storage.length) {
            System.out.println("Storage filled");
            return;
        } else if (index != -1) {
            System.out.println("This uuid: " + r.uuid + " is already in the storage");
            return;
        } else {
            storage[countResume++] = r;
        }
     }

     public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Storage has not this resume with uuid: " + uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index == -1) {
            System.out.println("Storage has not this resume with uuid: " + resume.uuid);
            return;
        }
        storage[index] = resume;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Storage has not this resume with uuid: " + uuid);
            return;
        }
        storage[index] = storage[countResume - 1];
        storage[countResume--] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
