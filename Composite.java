package DesignPatern.Bai1;

import java.util.List;
import java.util.Collections;

public interface Composite {

    // Phương thức để in thông tin của cá nhân hoặc nhóm
    void print();

    // Phương thức để kiểm tra xem đối tượng có phải là một nhóm hay không
    default boolean isGroup() {
        return false;
    }

    // Phương thức để kiểm tra xem đối tượng có kết hôn hay không (tuỳ chọn tuỳ theo
    // yêu cầu)
    default boolean isMarried() {
        return false;
    }

    // Phương thức để thêm một thành phần con (tuỳ chọn tuỳ theo yêu cầu)
    default void addChild(Composite child) {
        throw new UnsupportedOperationException("Operation not supported for this type");
    }

    // Phương thức để lấy danh sách các thành phần con (tuỳ chọn tuỳ theo yêu cầu)
    default List<Person> getChildren() {
        return Collections.emptyList();
    }
}
