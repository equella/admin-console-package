/**
 * Copyright <2019>, The Apereo Foundation
 *
 * This project includes software developed by The Apereo Foundation.
 * http://www.apereo.org/
 *
 * Licensed under the Apache License, Version 2.0, (the "License"); you may not
 * use this software except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apereo.openequella.adminconsole.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonService {
	
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	public static <T> T readFile(File file, Class<T> type){
		try (final InputStream fis = new FileInputStream(file)) {
			final T obj = jsonMapper.readValue(fis, type);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeFile(File file, Object obj){
		try (final OutputStream fos = new FileOutputStream(file)) {
            jsonMapper.writeValue(fos, obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}