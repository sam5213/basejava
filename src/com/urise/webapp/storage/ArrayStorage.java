package com.urise.webapp.storage;
import java.util.Arrays;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private int size;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage filled");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("This uuid: " + r.getUuid() + " is already in the storage");
        } else {
            storage[size++] = r;
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
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Storage has not this resume with uuid: " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Storage has not this resume with uuid: " + uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size--] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
