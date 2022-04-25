package programtervezesi_mintak;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.sameInstance;

import org.junit.jupiter.api.Test;

class StorageManagerTest {

	@Test
	void testGetInstance() {
		StorageManager storageManager1 = StorageManager.getInstance();
		StorageManager storageManager2 = StorageManager.getInstance();

		assertThat(storageManager1, sameInstance(storageManager2));
	}

}
