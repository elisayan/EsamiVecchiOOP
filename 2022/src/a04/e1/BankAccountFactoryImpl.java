package a04.e1;

import java.util.function.*;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createBasic() {
        return new BankAccount() {

            private int balance = 0;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (amount <= this.balance) {
                    this.balance -= amount;
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
        return new BankAccount() {

            private int balance = 0;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (amount <= this.balance) {
                    this.balance -= (amount + feeFunction.apply(amount));
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {

            private int balance = 0;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                // if (allowedCredit.test(amount) && amount <= balance) {
                // this.balance -= amount;
                // return true;
                // } else if (allowedCredit.test(amount - balance)) {
                // int rate = amount + rateFunction.apply(amount);
                // this.balance -= rate;
                // return true;
                // }

                // return false;
                if ((allowedCredit.test(amount) && amount <= this.balance)
                        || allowedCredit.test(amount - this.balance)) {
                    this.balance -= (amount <= this.balance) ? amount : amount + rateFunction.apply(amount);
                    return true;
                }

                return false;
            }

        };
    }

    @Override
    public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
        return new BankAccount() {

            private int balance = 0;
            private int overAmount;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if (blockingPolicy.test(amount, balance)) {
                    this.overAmount = amount;
                    return false;
                } else if (blockingPolicy.test(overAmount, balance)) {
                    return false;
                }
                this.balance -= amount;
                return true;

            }

        };
    }

    @Override
    public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
            UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {

            private int balance = 0;

            @Override
            public int getBalance() {
                return this.balance;
            }

            @Override
            public void deposit(int amount) {
                this.balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if ((allowedCredit.test(amount) && amount <= this.balance)
                        || allowedCredit.test(amount - this.balance)) {
                    this.balance -= (amount <= this.balance) ? amount + feeFunction.apply(amount)
                            : amount + rateFunction.apply(amount) + feeFunction.apply(amount);
                    return true;
                }

                return false;
            }

        };
    }

}
