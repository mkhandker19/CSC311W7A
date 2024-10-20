
    package org.example.javafxdb_sql_shellcode;

    import javafx.beans.value.ObservableValue;

    public class Person {
        private String firstName;
        private String lastName;
        private String department;
        private String major;

        public Person(String firstName, String lastName, String department, String major) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
            this.major = major;
        }

        // Getters and setters
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }

        public String getMajor() { return major; }
        public void setMajor(String major) { this.major = major; }

        public ObservableValue<String> firstNameProperty() {
            return null;
        }

        public ObservableValue<String> lastNameProperty() {
            return null;
        }

        public ObservableValue<String> departmentProperty() {
            return null;
        }

        public ObservableValue<String> majorProperty() {
            return null;
        }
    }

