package com.urise.webapp.storage;
import java.util.Arrays;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countResume;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
    }

    public void save(Resume r) {
        int resumeIndex = getResumeIndex(r.uuid);
        if (countResume == storage.length) {
            System.out.println("Storage filled");
            return;
        }
        if (resumeIndex != -1) {
            System.out.println("This uuid: " + r.uuid + " is already in the storage");
            return;
        }
        storage[countResume++] = r;
     }

     public Resume get(String uuid) {
        Resume resume = null;
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex == -1) {
            System.out.println("Storage has not this resume with uuid: " + uuid);
            return resume;
        }
        return storage[resumeIndex];
    }

    public void update(Resume resume) {
        int resumeIndex = getResumeIndex(resume.uuid);
        if (resumeIndex == -1) {
            System.out.println("Storage has not this resume with uuid: " + resume.uuid);
            return;
        }
        storage[resumeIndex] = resume;
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex == -1) {
            System.out.println("Storage has not this resume with uuid: " + uuid);
            return;
        }
        for (int i = resumeIndex; i < countResume - 1; i++) {
            storage[i] = storage[i + 1];
        }
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

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
