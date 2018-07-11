/*******************************************************************************
 * Copyright (C) by MinterTeam. 2018
 * @link https://github.com/MinterTeam
 * @link https://github.com/edwardstock
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/

package network.minter.bipwallet.tx.adapters.vh;

import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import network.minter.bipwallet.R;
import network.minter.bipwallet.tx.adapters.TxItem;
import network.minter.blockchainapi.models.operational.Transaction;
import network.minter.explorerapi.models.HistoryTransaction;

import static network.minter.bipwallet.internal.common.Preconditions.firstNonNull;

/**
 * minter-android-wallet. 2018
 *
 * @author Eduard Maximovich <edward.vstock@gmail.com>
 */
public final class TxDeclareCandidacyViewHolder extends ExpandableTxViewHolder {
    public @BindView(R.id.detail_pub_value) TextView pubKey;
    public @BindView(R.id.detail_address_value) TextView address;
    public @BindView(R.id.detail_commission_value) TextView commission;
    public @BindView(R.id.detail_coin_value) TextView coin;
    public @BindView(R.id.detail_stake_value) TextView stake;

    public TxDeclareCandidacyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(TxItem item) {
        super.bind(item);
        title.setText(item.getTx().hash.toShortString());
        amount.setText("Declare Candidacy");
        final HistoryTransaction.TxDeclareCandidacyResult data = item.getTx().getData();
        if (data.pubKey != null) {
            pubKey.setText(data.pubKey.toString());
        } else {
            pubKey.setText("<unknown>");
        }

        if (data.address != null) {
            address.setText(data.address.toString());
        } else {
            address.setText("<unknown>");
        }

        commission.setText(String.format("%s%%", firstNonNull(data.commission, new BigDecimal(0)).toPlainString()));
        coin.setText(data.getCoin());
        stake.setText(firstNonNull(data.stake, new BigDecimal(0)).divide(Transaction.VALUE_MUL_DEC).toPlainString());

    }
}