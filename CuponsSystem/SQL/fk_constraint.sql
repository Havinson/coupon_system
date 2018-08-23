use CouponDB;
alter table CompanyCoupon
add constraint fc_couponId foreign key (Coupon_ID) references Coupon(ID)
on delete cascade;