/*
 * Copyright (C) 2013 tarent AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.osiam.storage.entities;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.osiam.resources.scim.Photo;
import org.osiam.resources.type.PhotoType;

/**
 * Photos Entity
 */
@Entity(name = "scim_photo")
public class PhotoEntity extends MultiValueAttributeEntitySkeleton implements ChildOfMultiValueAttributeWithIdAndType<PhotoType>, HasUser, Serializable {

    private static final long serialVersionUID = -4535056565639057058L;

    //a valid photo url is everything which does not contain any control character and ends with jpg|jpeg|png|gif
    private static final Pattern PHOTO_SUFFIX = Pattern.compile("(?i)\\S+\\.(jpg|jpeg|png|gif)");

    @Column
    @Enumerated(EnumType.STRING)
    private PhotoType type;

    @ManyToOne
    private UserEntity user;

    @Override
    public void setValue(String value) {
        if(isValueIncorrect(value)) {
            throw new IllegalArgumentException("The photo MUST have an attribute 'value' that ends with " +
                    "JPEG, JPG, GIF, PNG.");
        }
        super.setValue(value);
    }

    private boolean isValueIncorrect(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return !PHOTO_SUFFIX.matcher(value).matches();
    }

    public PhotoType getType() {
        return type;
    }

    public void setType(PhotoType type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Photo toScim() {
        return new Photo.Builder().
                setType(getType()).
                setValue(getValue()).
                build();
    }

    public static PhotoEntity fromScim(Photo multiValuedAttribute) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setType(multiValuedAttribute.getType());
        photoEntity.setValue(String.valueOf(multiValuedAttribute.getValue()));
        return photoEntity;
    }

}